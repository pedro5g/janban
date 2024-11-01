import { DoorOpen, LogOut, Share2 } from "lucide-react";
import { useAuth } from "../../providers/auth-provider";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getRoomInfo, GetRoomInfoResponse } from "../../api/get-room-info";
export const Header = () => {
  const navigate = useNavigate();
  const { roomId } = useParams();
  const { getUser, signOut } = useAuth();
  const userName = getUser();
  const [roomData, setRoomData] = useState<GetRoomInfoResponse | null>(null);

  useEffect(() => {
    async function getRoomData() {
      if (!roomId) return;
      try {
        const result = await getRoomInfo(roomId);
        setRoomData(result);
      } catch (e) {
        console.log(e);
      }
    }

    getRoomData();
  });

  function handleShared() {
    if ("share" in navigator) {
      navigator.share({
        title: roomData?.roomName,
        url: `http://localhost:8080/app/board/${roomData?.roomId}`,
      });
    }
  }

  return (
    <header className="w-full h-15 fixed top-0 z-20 border-b-2 border-zinc-800 shadow-md">
      <div className="w-full h-full px-5 py-2 flex items-center justify-between">
        <h1 className="text-3xl text-white font-bold w-2/4">
          Hello, {userName?.name} 👋
        </h1>
        {roomId ? (
          <div className="text-center w-full">
            <p className=" text-white uppercase">{roomData?.roomName}</p>
            <span className="text-gray-400 text-sm cursor-pointer inline-flex gap-2 items-center">
              {roomData?.roomId}
              <span onClick={handleShared}>
                <Share2 size={14} />
              </span>
              <span onClick={() => navigate("/wizard")}>
                <DoorOpen size={14} className="text-red-500" />
              </span>
            </span>
          </div>
        ) : null}
        <div className=" w-2/4 flex justify-end">
          <button
            onClick={() => signOut()}
            className="text-white inline-flex items-center justify-center border border-gray-400/30 rounded p-2">
            <LogOut size={14} />
          </button>
        </div>
      </div>
    </header>
  );
};
