import { api } from "./axios";

export interface GetRoomInfoResponse {
  roomId: string;
  roomName: string;
}

export async function getRoomInfo(roomId: string) {
  const res = await api.get<GetRoomInfoResponse>(`/room/info/${roomId}`);

  return res.data;
}
