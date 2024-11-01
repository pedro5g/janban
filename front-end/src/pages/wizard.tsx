import { useState } from "react";
import { Button } from "../components/ui/button";
import { Input } from "../components/ui/input";
import { useNavigate } from "react-router-dom";
import { LoaderCircle } from "lucide-react";
import { z } from "zod";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { createRoom } from "../api/create-room";

const createRoomSchema = z.object({
  roomName: z
    .string()
    .min(6, { message: "Room name must be to minimum 6 characters" }),
});

type CreateRoomData = z.infer<typeof createRoomSchema>;

export function WizardPage() {
  const navigate = useNavigate();
  const [roodId, setRoomId] = useState("");

  const {
    register,
    handleSubmit,
    reset,
    formState: { errors, isLoading },
  } = useForm<CreateRoomData>({
    resolver: zodResolver(createRoomSchema),
    defaultValues: {
      roomName: "",
    },
  });

  const handleCreateRoom = async (data: CreateRoomData) => {
    try {
      // TO DO -> connect to the api
      const res = await createRoom(data);
      reset({ roomName: "" });
      navigate(`/board/${res.roomId}`);
    } catch (e) {
      console.error(e);
    }
  };

  return (
    <div className=" w-full h-screen flex items-center justify-center">
      <div className="max-w-[680px] px-2 py-4 w-full space-y-3 rounded border-2 border-zinc-700 bg-zinc-800 p-3">
        <h1 className="text-center text-2xl text-white font-bold">
          Do you already know what to do? 🤔
        </h1>
        <div className="min-w-full grid grid-cols-2 grid-rows-10 gap-2">
          <div className="flex flex-col col-span-1 row-span-10 px-2 py-4 space-y-4 ">
            <h2 className="text-left text-white font-semibold text-lg">
              Enter a room
            </h2>
            <Input
              onChange={(e) => setRoomId(e.target.value)}
              defaultValue={roodId}
              placeholder="Room id"
            />
            <div className="w-1/3">
              <Button
                onClick={() => navigate(`/board/${roodId}`)}
                type="button">
                To enter
              </Button>
            </div>
          </div>
          <div className="flex flex-col col-span-1 row-span-10 px-2 py-4 space-y-4 ">
            <h2 className="text-left text-white font-semibold text-lg">
              Create Room
            </h2>
            <form
              onSubmit={handleSubmit(handleCreateRoom)}
              className="space-y-4">
              <Input
                {...register("roomName", { required: true })}
                placeholder="Room name"
                message={errors["roomName"]?.message}
              />
              <div className="w-1/3">
                <Button disabled={isLoading} type="submit">
                  {!isLoading ? (
                    "Create"
                  ) : (
                    <LoaderCircle size={20} className="animate-spin" />
                  )}
                </Button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}
