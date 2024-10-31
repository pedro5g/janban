import { api } from "./axios";

interface CreateNoteData {
  title: string;
  column: "done" | "backlog" | "doing" | "todo";
}

interface ResponseCreateNoteBody {
  id: string;
  title: string;
  position: string;
  column: "done" | "backlog" | "doing" | "todo";
  createdAt: Date;
}

export const createNote = async (data: CreateNoteData) => {
  const res = await api.post<ResponseCreateNoteBody>("/note/create", {
    title: data.title,
    column: data.column,
  });
  return res.data;
};
