interface DropIndicatorProps {
  beforeId: string;
  column: "done" | "backlog" | "doing" | "todo";
}

export const DropIndicator = ({ column, beforeId }: DropIndicatorProps) => {
  return (
    <div
      data-before={beforeId}
      data-column={column}
      className=" my-0.5 h-0.5 w-full bg-violet-500 opacity-0"
    />
  );
};
