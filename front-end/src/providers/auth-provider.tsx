import {
  createContext,
  useContext,
  useEffect,
  useLayoutEffect,
  useState,
} from "react";
import { getProfile } from "../api/get-profile";
import { useNavigate } from "react-router-dom";
import { api } from "../api/axios";
import { isAxiosError } from "axios";

type User = {
  name: string;
  email: string;
};

interface AuthContentProps {
  setSession: (toke: string) => void;
  getUser: () => User | null;
  getToken: () => string;
  signOut: () => void;
}

const AuthContent = createContext<AuthContentProps | null>(null);

export const AuthProvider = ({
  children,
}: Readonly<{ children: React.ReactNode }>) => {
  const STORAGE_KEY = "__access_token__";

  const navigate = useNavigate();
  const [user, setUser] = useState<User | null>(null);
  const [token, setToken] = useState(() => getAccessToken());

  useEffect(() => {
    async function fetchMe() {
      try {
        const result = await getProfile();
        setUser(result);
      } catch (e) {
        setUser(null);
      }
    }

    fetchMe();
  }, [token]);

  useLayoutEffect(() => {
    const authInterceptor = api.interceptors.request.use((config) => {
      config.headers.Authorization = token ? `Bearer ${token}` : undefined;
      return config;
    });

    return () => {
      api.interceptors.request.eject(authInterceptor);
    };
  }, [token]);

  useLayoutEffect(() => {
    const interceptorId = api.interceptors.response.use(
      (response) => response,
      (error) => {
        if (isAxiosError(error)) {
          const status = error.response?.status;

          if (status === 401 || status === 403 || status === 404) {
            deleteToken();
            navigate("/sign-in");
          }
        }

        return Promise.reject(error);
      }
    );

    // Clean up the side effect when the component unmounts
    return () => {
      api.interceptors.response.eject(interceptorId);
    };
  }, [navigate]);

  function getAccessToken(): string {
    return localStorage.getItem(STORAGE_KEY) ?? "";
  }

  function salveAccessToken(token: string): void {
    localStorage.setItem(STORAGE_KEY, token);
  }

  function deleteToken(): void {
    localStorage.removeItem(STORAGE_KEY);
  }

  const getUser = () => {
    return user;
  };

  const getToken = () => {
    return token;
  };

  const setSession = (token: string) => {
    setToken(() => {
      salveAccessToken(token);
      return token;
    });
  };

  const signOut = () => {
    setToken(() => {
      deleteToken();
      navigate("/sign-in");
      return "";
    });
    setUser(null);
  };

  return (
    <AuthContent.Provider
      value={{
        getToken,
        getUser,
        setSession,
        signOut,
      }}>
      {children}
    </AuthContent.Provider>
  );
};

export const useAuth = () => {
  const authContext = useContext(AuthContent);

  if (!authContext) {
    throw new Error("useAuth must be used within AuthProvider");
  }

  return authContext;
};
