import { api } from "./axios";

interface RequestGetNotes {
  roomId: string;
}
interface ResponseGetNotesBody {
  id: string;
  title: string;
  authorName: string;
  position: number;
  column: "done" | "backlog" | "doing" | "todo";
  createdAt: Date;
}

export const getNotes = async ({ roomId }: RequestGetNotes) => {
  const res = await api.get<ResponseGetNotesBody[]>(`/note/get-all/${roomId}`);
  return res.data;
};
