import { Link, useNavigate } from "react-router-dom";
import { Button } from "../../components/ui/button";
import { Input } from "../../components/ui/input";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { registerNewUser } from "../../api/register";
import { z } from "zod";

const registerFormSchema = z.object({
  name: z
    .string({ message: "Please, give a name!" })
    .trim()
    .min(3, { message: "The name must be to minimum 3 characters" }),
  email: z.string().trim().email({ message: "Invalid email" }),
  password: z
    .string()
    .trim()
    .min(6, { message: "Password must be to minimum 6 characters" })
    .max(100, {
      message: "Password must be to max 100 characters",
    }),
});

type RegisterFormSchema = z.infer<typeof registerFormSchema>;

export function SignUpPage() {
  const navigation = useNavigate();

  const {
    setError,
    reset,
    register,
    formState: { errors, isLoading },
    handleSubmit,
  } = useForm<RegisterFormSchema>({
    resolver: zodResolver(registerFormSchema),
    defaultValues: {
      name: "",
      email: "",
      password: "",
    },
  });

  const handlerRegister = async (data: RegisterFormSchema) => {
    try {
      await registerNewUser(data);
      navigation("/sign-in");
      reset({
        name: "",
        email: "",
        password: "",
      });
    } catch (e) {
      setError("email", { message: "Email already exists" });
    }
  };

  return (
    <div className=" w-full h-screen flex items-center justify-center">
      <div className="max-w-[380px] px-5 py-4 w-full rounded border-2 border-zinc-700 bg-zinc-800 p-3">
        <h1 className="text-center text-3xl text-white font-bold">SignUp</h1>
        <form
          onSubmit={handleSubmit(handlerRegister)}
          className="w-full space-y-4 mt-4">
          <Input
            {...register("name", { required: true })}
            message={errors["name"]?.message}
            placeholder="Name"
            type="text"
          />
          <Input
            {...register("email", { required: true })}
            message={errors["email"]?.message}
            placeholder="Email"
            type="text"
          />
          <Input
            {...register("password", { required: true })}
            message={errors["password"]?.message}
            placeholder="Password"
            type="password"
          />
          <Button type="submit" disabled={isLoading}>
            Register
          </Button>
        </form>
        <p className="inline-flex mt-3 font-light text-white text-sm items-center">
          Do have an account yet ?
          <span className="ml-2 font-bold text-violet-400 hover:underline">
            <Link to={"/sign-in"}>Sign In</Link>
          </span>
        </p>
      </div>
    </div>
  );
}
