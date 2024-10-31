import { api } from "./axios";

interface ResponseGetNotesBody {
  id: string;
  title: string;
  position: string;
  column: "done" | "backlog" | "doing" | "todo";
  createdAt: Date;
}

export const getNotes = async () => {
  const res = await api.get<ResponseGetNotesBody[]>("/note/get-all");
  return res.data;
};
