export type NoteType = {
  id: string;
  title: string;
  column: "done" | "backlog" | "doing" | "todo";
  // createdAt: Date;
};
