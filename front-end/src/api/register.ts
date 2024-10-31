import { api } from "./axios";

interface RequestRegisterBody {
  name: string;
  email: string;
  password: string;
}

export const registerNewUser = async (data: RequestRegisterBody) => {
  const res = await api.post("/user/register", {
    name: data.name,
    email: data.email,
    password: data.password,
  });

  if (res.status !== 200) {
    throw new Error(res.data);
  }
};
