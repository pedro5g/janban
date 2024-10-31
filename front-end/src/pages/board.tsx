import { useEffect, useState } from "react";
import { Column } from "../components/column";
import { NoteType } from "../types/notes-type";
// import { DEV_DEFAULT_NOTES } from "../config/dev-default-notes";
import { Trash } from "../components/trash";
import { getNotes } from "../api/get-notes";

export const Board = () => {
  const [cards, setCards] = useState<NoteType[]>([]);

  useEffect(() => {
    async function fetchData() {
      try {
        const data = (await getNotes()) ?? [];
        setCards(data);
      } catch (e) {
        console.error(e);
      }
    }

    fetchData();
  }, []);

  return (
    <section className=" flex gap-3 w-full h-full justify-center p-12 pt-20">
      <Column
        title="Backlog"
        column="backlog"
        headingColor="text-zinc-500"
        cards={cards}
        setCards={setCards}
      />
      <Column
        title="To Do"
        column="todo"
        headingColor="text-yellow-500"
        cards={cards}
        setCards={setCards}
      />
      <Column
        title="In Progress"
        column="doing"
        headingColor="text-blue-200"
        cards={cards}
        setCards={setCards}
      />
      <Column
        title="Complete"
        column="done"
        headingColor="text-emerald-200"
        cards={cards}
        setCards={setCards}
      />
      <Trash setCards={setCards} />
    </section>
  );
};
