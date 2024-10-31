import { api } from "./axios";

interface ResponseGetProfileBody {
  name: string;
  email: string;
}

export const getProfile = async () => {
  const res = await api.get<ResponseGetProfileBody>("/user/me");
  return res.data;
};
