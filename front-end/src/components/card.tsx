import { NoteType } from "../types/notes-type";
import { DropIndicator } from "./drop-indicator";
import { motion } from "framer-motion";
interface CardProps {
  id: string;
  title: string;
  column: "done" | "backlog" | "doing" | "todo";
  authorName: string;
  handleDragStart: (e: React.DragEvent, card: NoteType) => void;
}

export const Card = ({
  id,
  title,
  column,
  authorName,
  handleDragStart,
}: CardProps) => {
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
            authorName,
          })
        }
        className=" relative cursor-grab rounded border-2 border-zinc-700 bg-zinc-800 p-3 active:cursor-grabbing">
        <p className=" text-sm text-zinc-100 ">{title}</p>
        <span className=" absolute bottom-1 right-3 text-[12px] text-purple-500 font-light lowercase leading-none">
          {authorName}
        </span>
      </motion.div>
    </>
  );
};
