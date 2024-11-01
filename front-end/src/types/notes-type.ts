export type NoteType = {
  id: string;
  title: string;
  authorName: string;
  column: "done" | "backlog" | "doing" | "todo";
  // createdAt: Date;
};
