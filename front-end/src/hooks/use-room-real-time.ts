import { Stomp } from "@stomp/stompjs";
import { useEffect } from "react";
import SockJS from "sockjs-client";
import { NoteType, WebSocketMessage } from "../types/notes-type";

export interface UseRoomRealTimeProps {
  roomId?: string;
  setCards: React.Dispatch<React.SetStateAction<NoteType[]>>;
}

export function useRoomRealTime({ roomId, setCards }: UseRoomRealTimeProps) {
  useEffect(() => {
    if (!roomId) return;

    const client = Stomp.over(() => new SockJS("http://localhost:8080/ws"));

    client.connect({}, () => {
      client.subscribe(`/note/room/${roomId}`, (message) => {
        const messageBody = JSON.parse(message.body) as WebSocketMessage;
        console.log(messageBody.data);
        switch (messageBody.type) {
          case "created":
            setCards((prev) => {
              return [...prev, messageBody.data];
            });
            break;
          case "deleted":
            setCards((prev) => {
              const cards = prev.filter((c) => c.id !== messageBody.data);
              return cards;
            });
            break;
          case "updated":
            setCards((prev) => {
              const cards = prev.filter((c) => c.id !== messageBody.data.id);
              if (messageBody.data.position === -1) {
                cards.push(messageBody.data);
              } else {
                cards.splice(messageBody.data.position, 0, messageBody.data);
              }
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
}
