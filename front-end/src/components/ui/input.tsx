import React from "react";
import { AlertTriangle } from "lucide-react";

interface InputProps extends React.ComponentProps<"input"> {
  message?: string;
}

export const Input = React.forwardRef<HTMLInputElement, InputProps>(
  ({ message, ...props }, ref) => {
    return (
      <div className="space-y-2">
        <input
          {...props}
          ref={ref}
          className="w-full py-2 pl-5 rounded-md bg-zinc-800 shadow-md ring-2 ring-zinc-700 text-white required:ring-red-500"
        />
        {message && (
          <span className="text-red-400 text-sm font-light inline-flex items-center justify-center">
            <AlertTriangle size={14} className="mr-2" />
            <p className="leading-3">{message}</p>
          </span>
        )}
      </div>
    );
  }
);
