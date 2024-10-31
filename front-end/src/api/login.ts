import { api } from "./axios";

interface RequestLoginData {
  email: string;
  password: string;
}

interface ResponseLoginBody {
  access_token: string;
}

export const login = async (data: RequestLoginData) => {
  const res = await api.post<ResponseLoginBody>("/user/auth", {
    email: data.email,
    password: data.password,
  });

  return res.data;
};
