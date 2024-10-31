import { api } from "./axios";

interface UpdateNoteData {
  id: string;
  title: string;
  column: "done" | "backlog" | "doing" | "todo";
}

export const updateNote = async ({ id, title, column }: UpdateNoteData) => {
  return await api.patch(`/note/${id}`, {
    title,
    column,
  });
};
