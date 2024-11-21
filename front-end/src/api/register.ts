import { api } from "./axios";

interface RequestRegisterBody {
  name: string;
  email: string;
  password: string;
}

export const registerNewUser = async (data: RequestRegisterBody) => {
  await api.post("/user/register", {
    name: data.name,
    email: data.email,
    password: data.password,
  });
};
