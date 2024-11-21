import { LoaderCircle, Plus } from "lucide-react";
import { useState } from "react";
import { motion } from "framer-motion";
import { z } from "zod";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { createNote } from "../api/create-note";
import { useParams } from "react-router-dom";

interface AddNewCardProps {
  column: "done" | "backlog" | "doing" | "todo";
}

const newCardFormSchema = z.object({
  title: z.string().trim().min(1, { message: "must not be empty" }),
  column: z.enum(["done", "backlog", "doing", "todo"]),
  roomId: z.string().uuid(),
});

type NewCardFormSchema = z.infer<typeof newCardFormSchema>;

export const AddNewCard = ({ column }: AddNewCardProps) => {
  const { roomId } = useParams();
  const [adding, setAdding] = useState(false);

  const {
    handleSubmit,
    register,
    reset,
    formState: { isLoading },
  } = useForm<NewCardFormSchema>({
    resolver: zodResolver(newCardFormSchema),
    values: {
      title: "",
      column: column,
      roomId: roomId ?? "",
    },
  });

  async function handleCreateNote(data: NewCardFormSchema) {
    try {
      await createNote(data);
    } catch (e) {
      console.error(e);
    } finally {
      reset({ title: "", column: column, roomId: roomId ?? "" });
      setAdding(false);
    }
  }

  return (
    <>
      {adding ? (
        <motion.div layout>
          <form onSubmit={handleSubmit(handleCreateNote)}>
            <textarea
              {...register("title", { required: true })}
              placeholder="Add new task..."
              autoFocus={true}
              className="w-full rounded border-2 text-zinc-50 border-violet-400 bg-violet-400/20 p-3 text-sm 
              placeholder-violet-300 focus:outline-0 overflow-y-hidden invalid:border-red-400 invalid:bg-red-400/20"
            />
            <div className="mt-1.5 flex items-center justify-end gap-1.5">
              <button
                type="button"
                onClick={() => setAdding(false)}
                className="px-3 py-1.5 text-xs text-zinc-400 transition-colors hover:text-zinc-50">
                Close
              </button>
              <button
                type="submit"
                className="flex items-center gap-1.5 rounded bg-zinc-50 px-3 py-1.5 text-xs text-zinc-950 
                transition-colors hover:bg-zinc-300 font-semibold">
                {!isLoading ? (
                  <>
                    <span>Add</span>
                    <Plus size={20} />
                  </>
                ) : (
                  <LoaderCircle size={20} className="animate-spin" />
                )}
              </button>
            </div>
          </form>
        </motion.div>
      ) : (
        <motion.button
          layout
          onClick={() => setAdding(true)}
          className=" flex w-full items-center gap-1.5 px-3 py-1.5 text-xs text-zinc-400 transition-colors hover:text-zinc-50">
          <Plus />
          <span>Add Note</span>
        </motion.button>
      )}
    </>
  );
};
