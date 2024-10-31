import { createBrowserRouter, RouterProvider } from "react-router-dom";
import { Board } from "./pages/board";
import { SignIn } from "./pages/auth/sign-in";
import { SignUpPage } from "./pages/auth/sing-up";
import { AppLayout } from "./pages/_layouts/app-layout";
import { RootLayout } from "./pages/_layouts/root-layout";

const routes = createBrowserRouter(
  [
    {
      path: "/",
      element: <RootLayout />,
      children: [
        {
          path: "/",
          element: <AppLayout />,
          children: [
            {
              path: "/",
              element: <Board />,
            },
          ],
        },
        {
          path: "/sign-in",
          element: <SignIn />,
        },
        {
          path: "/sign-up",
          element: <SignUpPage />,
        },
      ],
    },
  ],
  {
    basename: "/app",
    patchRoutesOnNavigation: async (opts) => {
      const a = document.createElement("a");
      a.href = "/app";
      //prevent to access in inexistente routes
      if (opts.path === "/") {
        a.click();
      }

      const paths = ["/app", "/sign-up", "/sign-in"];

      if (!paths.includes(opts.path)) {
        a.click();
      }
    },
  }
);

export const App = () => {
  return <RouterProvider router={routes} />;
};
