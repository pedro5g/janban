interface ButtonProps extends React.ComponentProps<"button"> {
  label?: string;
}

export const Button = ({ label, children, ...props }: ButtonProps) => {
  return (
    <button
      {...props}
      className="inline-flex w-full items-center justify-center py-2 text-white font-semibold bg-violet-500 
          rounded-md ring-2 ring-violet-400 transition-colors hover:bg-violet-600 disabled:opacity-80">
      {label ? label : children}
    </button>
  );
};
