import { LogOut } from "lucide-react";
import { useAuth } from "../../providers/auth-provider";
export const Header = () => {
  const { getUser, signOut } = useAuth();
  const userName = getUser();
  return (
    <header className="w-full h-15 fixed top-0 z-20 border-b-2 border-zinc-800 shadow-md">
      <div className="w-full h-full px-5 py-2 flex items-center justify-between">
        <h1 className="text-3xl text-white font-bold">
          Hello, {userName?.name} 👋
        </h1>
        <button
          onClick={() => signOut()}
          className="text-white inline-flex items-center justify-center border border-gray-400/30 rounded p-2">
          <LogOut size={14} />
        </button>
      </div>
    </header>
  );
};
