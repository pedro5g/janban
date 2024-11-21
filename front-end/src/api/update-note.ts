import { api } from "./axios";

interface UpdateNoteData {
  id: string;
  title: string;
  column: "done" | "backlog" | "doing" | "todo";
  position: number;
}

export const updateNote = async ({
  id,
  title,
  column,
  position,
}: UpdateNoteData) => {
  return await api.patch(`/note/${id}`, {
    title,
    column,
    position,
  });
};
