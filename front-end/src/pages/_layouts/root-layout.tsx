import { Outlet } from "react-router-dom";
import { AuthProvider } from "../../providers/auth-provider";

export function RootLayout() {
  return (
    <AuthProvider>
      <div className="relative w-full flex justify-center">
        <Outlet />
      </div>
    </AuthProvider>
  );
}
