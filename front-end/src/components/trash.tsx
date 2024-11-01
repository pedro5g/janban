import { Flame, Trash as Trash1 } from "lucide-react";
import { useState } from "react";
import { deleteNote } from "../api/delete-note";

export const Trash = () => {
  const [active, setActive] = useState(false);
  function handleDragOver(e: React.DragEvent) {
    e.preventDefault();
    setActive(true);
  }
  function handleDragLeave(e: React.DragEvent) {
    e.preventDefault();
    setActive(false);
  }
  async function handleDragEnd(e: React.DragEvent<HTMLDivElement>) {
    try {
      const cardId = e.dataTransfer.getData("cardId");
      await deleteNote(cardId);
    } catch (e) {
      console.error(e);
    } finally {
      setActive(false);
    }
  }
  return (
    <div
      onDragOver={handleDragOver}
      onDragLeave={handleDragLeave}
      onDrop={handleDragEnd}
      data-active={active}
      className="col-span-1 mt-10 grid w-56 h-56
        place-content-center rounded border-2 
        data-[active=true]:border-red-800 data-[active=true]:bg-red-800/20 data-[active=true]:text-red-500
        data-[active=false]:border-zinc-500 data-[active=false]:bg-zinc-500/20 data-[active=false]:text-zinc-500
        ">
      {active ? (
        <Flame className="animate-bounce" size={30} />
      ) : (
        <Trash1 size={30} />
      )}
    </div>
  );
};
