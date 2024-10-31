import { Link, useNavigate } from "react-router-dom";
import { Button } from "../../components/ui/button";
import { Input } from "../../components/ui/input";
import { z } from "zod";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { login } from "../../api/login";
import { useAuth } from "../../providers/auth-provider";

const loginFormSchema = z.object({
  email: z.string().min(1),
  password: z.string().min(1),
});

type LoginFormSchema = z.infer<typeof loginFormSchema>;

export function SignIn() {
  const { setSession } = useAuth();
  const navigation = useNavigate();

  const {
    reset,
    register,
    formState: { isLoading, errors },
    setError,
    handleSubmit,
  } = useForm<LoginFormSchema>({
    resolver: zodResolver(loginFormSchema),
    defaultValues: {
      email: "",
      password: "",
    },
  });

  const handlerLogin = async (data: LoginFormSchema) => {
    try {
      const res = await login(data);
      setSession(res.access_token);
      navigation("/", { viewTransition: true });
      reset({
        email: "",
        password: "",
      });
    } catch (e) {
      setError("root", { message: " Email or password are invalid" });
    }
  };

  return (
    <div className=" w-full h-screen flex items-center justify-center">
      <div className="max-w-[380px] px-5 py-4 w-full rounded border-2 border-zinc-700 bg-zinc-800 p-3">
        <h1 className="text-center text-3xl text-white font-bold">SignIn</h1>
        <form
          onSubmit={handleSubmit(handlerLogin)}
          className="w-full space-y-4 mt-4">
          <Input
            placeholder="Email"
            type="email"
            {...register("email")}
            message={errors["root"]?.message}
          />
          <Input
            placeholder="Password"
            type="password"
            {...register("password")}
            message={errors["root"]?.message}
          />
          <Button disabled={isLoading} type="submit">
            To enter
          </Button>
        </form>
        <p className="inline-flex mt-3 font-light text-white text-sm items-center">
          Don't have an account yet ?
          <span className="ml-2 font-bold text-violet-400 hover:underline">
            <Link to={"/sign-up"}>Sing up</Link>
          </span>
        </p>
      </div>
    </div>
  );
}
