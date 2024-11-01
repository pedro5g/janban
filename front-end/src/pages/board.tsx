import { useEffect, useState } from "react";
import { Column } from "../components/column";
import { NoteType } from "../types/notes-type";
// import { DEV_DEFAULT_NOTES } from "../config/dev-default-notes";
import { Trash } from "../components/trash";
import { getNotes } from "../api/get-notes";
import { useParams } from "react-router-dom";
import { Stomp } from "@stomp/stompjs";
import SockJS from "sockjs-client";

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

  useEffect(() => {
    const client = Stomp.over(() => new SockJS("http://localhost:8080/ws"));
    client.connect({}, () => {
      console.log("STOMP connected");

      client.subscribe(`/note/room/${roomId}`, (message) => {
        const annotation = JSON.parse(message.body);
        console.log("Recebeu anotação:", annotation);

        switch (annotation.type) {
          case "created":
            setCards((prev) => {
              return [...prev, annotation.data];
            });
            break;
          case "deleted":
            setCards((prev) => {
              const cards = prev.filter((c) => c.id !== annotation.data);
              return cards;
            });
            break;
          case "updated":
            setCards((prev) => {
              const cards = prev.map((c) => {
                if (c.id === annotation.data.id) {
                  return annotation.data;
                }
                return c;
              });

              return cards;
            });
            break;
          default:
            break;
        }
      });
    });

    return () => {
      if (client) client.disconnect();
    };
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
      <Trash />
    </section>
  );
};
