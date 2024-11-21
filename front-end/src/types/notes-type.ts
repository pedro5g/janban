export type NoteType = {
  id: string;
  title: string;
  authorName: string;
  column: "done" | "backlog" | "doing" | "todo";
  position: number;
  // createdAt: Date;
};

export type WebSocketMessage =
  | { type: "created"; data: NoteType }
  | { type: "deleted"; data: string }
  | { type: "updated"; data: NoteType };
