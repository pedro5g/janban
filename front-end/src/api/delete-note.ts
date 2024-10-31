import { api } from "./axios";

export async function deleteNote(nodeId: string) {
  await api.delete(`/note/${nodeId}`);
}
