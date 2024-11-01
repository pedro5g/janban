import { api } from "./axios";

interface CreateRoomRequest {
  roomName: string;
}

interface CreateRoomResponse {
  roomId: string;
  roomName: string;
}

export async function createRoom(data: CreateRoomRequest) {
  const res = await api.post<CreateRoomResponse>("/room/create", {
    roomName: data.roomName,
  });

  return res.data;
}
