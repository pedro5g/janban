import { LoaderCircle, Plus } from "lucide-react";
import { useState } from "react";
import { NoteType } from "../types/notes-type";
import { motion } from "framer-motion";
import { z } from "zod";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { createNote } from "../api/create-note";

interface AddNewCardProps {
  column: "done" | "backlog" | "doing" | "todo";
  setCards: React.Dispatch<React.SetStateAction<NoteType[]>>;
}

const newCardFormSchema = z.object({
  title: z
    .string()
    .min(1, { message: "must not be empty" })
    .transform((text) => text.trim()),
  column: z.enum(["done", "backlog", "doing", "todo"]),
});

type NewCardFormSchema = z.infer<typeof newCardFormSchema>;

export const AddNewCard = ({ column, setCards }: AddNewCardProps) => {
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
    },
  });

  async function handleCreateNote(data: NewCardFormSchema) {
    try {
      const res = await createNote(data);
      setCards((prev) => [...prev, res]);
    } catch (e) {
      console.error(e);
    } finally {
      reset({ title: "", column: column });
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
              className="w-full rounded border-2 text-zinc-50 border-violet-400 bg-violet-400/20 p-3 text-sm 
              placeholder-violet-300 focus:outline-0 overflow-y-hidden"
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
