import { api } from "./axios";

interface CreateNoteData {
  title: string;
  column: "done" | "backlog" | "doing" | "todo";
  roomId: string;
}

interface ResponseCreateNoteBody {
  id: string;
  title: string;
  column: "done" | "backlog" | "doing" | "todo";
  createdAt: Date;
}

export const createNote = async (data: CreateNoteData) => {
  const res = await api.post<ResponseCreateNoteBody>("/note/create", {
    title: data.title,
    column: data.column,
    roomId: data.roomId,
  });
  return res.data;
};
