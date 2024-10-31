import { Outlet } from "react-router-dom";
import { Header } from "../../components/ui/header";

export function AppLayout() {
  return (
    <>
      <Header />
      <Outlet />
    </>
  );
}
