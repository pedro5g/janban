import { useEffect, useState } from "react";
import { Column } from "../components/column";
import { NoteType } from "../types/notes-type";
import { Trash } from "../components/trash";
import { getNotes } from "../api/get-notes";
import { useParams } from "react-router-dom";
import { useRoomRealTime } from "../hooks/use-room-real-time";

export const Board = () => {
  const { roomId } = useParams();
  const [cards, setCards] = useState<NoteType[]>([]);

  useEffect(() => {
    async function fetchData() {
      if (!roomId) return;
      try {
        const data = (await getNotes({ roomId })) ?? [];
        setCards(data);
      } catch (e) {
        console.error(e);
      }
    }

    fetchData();
  }, []);

  useRoomRealTime({ roomId, setCards });

  return (
    <section className=" flex gap-3 min-w-[1300px] w-full h-full justify-center p-12 pt-20">
      <Column
        title="Backlog"
        column="backlog"
        headingColor="text-zinc-500"
        cards={cards}
      />
      <Column
        title="To Do"
        column="todo"
        headingColor="text-yellow-500"
        cards={cards}
      />
      <Column
        title="In Progress"
        column="doing"
        headingColor="text-blue-200"
        cards={cards}
      />
      <Column
        title="Complete"
        column="done"
        headingColor="text-emerald-200"
        cards={cards}
      />
      <Trash />
    </section>
  );
};
