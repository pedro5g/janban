import { NoteType } from "../types/notes-type";
import { DropIndicator } from "./drop-indicator";
import { motion } from "framer-motion";
interface CardProps {
  id: string;
  title: string;
  column: "done" | "backlog" | "doing" | "todo";
  handleDragStart: (e: React.DragEvent, card: NoteType) => void;
}

export const Card = ({ id, title, column, handleDragStart }: CardProps) => {
  return (
    <>
      <DropIndicator beforeId={id} column={column} />
      <motion.div
        layout
        layoutId={id}
        draggable="true"
        onDragStart={(e) =>
          handleDragStart(e as unknown as React.DragEvent, {
            id,
            column,
            title,
          })
        }
        className=" cursor-grab rounded border-2 border-zinc-700 bg-zinc-800 p-3 active:cursor-grabbing">
        <p className=" text-sm text-zinc-100">{title}</p>
      </motion.div>
    </>
  );
};
