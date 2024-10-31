import { useEffect, useRef, useState } from "react";
import { NoteType } from "../types/notes-type";
import { Card } from "./card";
import { DropIndicator } from "./drop-indicator";
import { AddNewCard } from "./add-new-card";
import { updateNote } from "../api/update-note";

interface ColumnProps {
  title: string;
  headingColor: string;
  column: "done" | "backlog" | "doing" | "todo";
  cards: NoteType[];
  setCards: React.Dispatch<React.SetStateAction<NoteType[]>>;
}

export const Column = ({
  title,
  headingColor,
  column,
  cards,
  setCards,
}: ColumnProps) => {
  const [active, setActive] = useState(false);
  const indicatorsRef = useRef<HTMLElement[]>([]);

  // SELECT ALL ELEMENTS WITHIN DOM THAT CONTAINS `data-column` ATTRIBUTES
  useEffect(() => {
    indicatorsRef.current = Array.from(
      document.querySelectorAll(`[data-column="${column}"]`)
    );
  }, [column, handleDragEnd]);

  // set the data to transfer
  const handleDragStart = (e: React.DragEvent, card: NoteType) => {
    e.dataTransfer.setData("cardId", card.id);
  };

  function handleDragOver(e: React.DragEvent) {
    e.preventDefault();
    handlingIndicator(e);
    setActive(true);
  }

  function handlingIndicator(e: React.DragEvent) {
    const indicators = indicatorsRef.current;
    clearIndicators(indicators);
    const el = getNearsIndicator(e, indicators);
    el.element.style.opacity = "1";
  }

  const clearIndicators = (els?: HTMLElement[]) => {
    const indicatores = els || indicatorsRef.current;

    indicatores.forEach((ind) => {
      ind.style.opacity = "0";
    });
  };

  const getNearsIndicator = (e: React.DragEvent, indicators: HTMLElement[]) => {
    const DISTANCE_OFFSET = 50;

    const el = indicators.reduce(
      (closest, child) => {
        const box = child.getBoundingClientRect();

        const offset = e.clientY - (box.top + DISTANCE_OFFSET);

        if (offset < 0 && offset > closest.offset) {
          return { offset: offset, element: child };
        } else {
          return closest;
        }
      },
      {
        offset: Number.NEGATIVE_INFINITY,
        element: indicators[indicators.length - 1],
      }
    );

    return el;
  };

  function handleDragLeave() {
    setActive(false);
    clearIndicators();
  }

  async function handleDragEnd(e: React.DragEvent) {
    const cardId = e.dataTransfer.getData("cardId");
    setActive(false);
    clearIndicators();

    const { element } = getNearsIndicator(e, indicatorsRef.current);

    const before = element.dataset.before || "-1";

    if (before !== cardId) {
      let copy = [...cards];

      let cardToTransfer = copy.find((c) => c.id === cardId);

      if (!cardToTransfer) return;
      cardToTransfer = { ...cardToTransfer, column };

      copy = copy.filter((c) => c.id !== cardId);

      const moveToBack = before === "-1";

      if (moveToBack) {
        copy.push(cardToTransfer);
      } else {
        const insertAtIndex = copy.findIndex((el) => el.id === before);
        if (insertAtIndex < 0) return;
        copy.splice(insertAtIndex, 0, cardToTransfer);
      }

      try {
        await updateNote(cardToTransfer);
        setCards(copy);
      } catch (e) {
        console.error(e);
      }
    }
  }

  /// list all cards to current column
  const filteredCards = cards.filter((note) => note.column === column);

  return (
    <div className=" w-56 rounded-md shrink-0">
      <div className="mb-3 flex items-center justify-between">
        <h1 className={`font-medium ${headingColor}`}>{title}</h1>
        <span className=" rounded text-sm text-zinc-400">
          {filteredCards.length}
        </span>
      </div>
      <div
        onDragOver={handleDragOver}
        onDragLeave={handleDragLeave}
        onDrop={handleDragEnd}
        data-active={active}
        className="h-full w-full py-1 rounded-md gap-2 transition-colors data-[active=true]:bg-zinc-900/50 data-[active=false]:bg-zinc-950 ">
        {filteredCards.map((card) => {
          return (
            <Card
              key={card.id}
              id={card.id}
              title={card.title}
              column={card.column}
              handleDragStart={handleDragStart}
            />
          );
        })}
        <DropIndicator beforeId={"-1"} column={column} />
        <AddNewCard setCards={setCards} column={column} />
      </div>
    </div>
  );
};
