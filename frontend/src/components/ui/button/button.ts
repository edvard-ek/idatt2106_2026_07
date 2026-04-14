import { cva, type VariantProps } from 'class-variance-authority'

export const buttonVariants = cva(
  'inline-flex items-center justify-center gap-2 whitespace-nowrap rounded-xl text-sm font-semibold transition focus-visible:outline-none focus-visible:ring-4 focus-visible:ring-sky-100 disabled:pointer-events-none disabled:opacity-50',
  {
    variants: {
      variant: {
        default: 'bg-slate-950 text-white shadow-lg shadow-slate-950/15 hover:bg-slate-800',
        outline: 'border border-slate-200 bg-white text-slate-950 hover:bg-slate-100',
        ghost: 'text-slate-700 hover:bg-slate-100',
      },
      size: {
        default: 'h-10 px-4 py-2',
        lg: 'h-12 px-6 text-base',
        icon: 'h-10 w-10',
      },
    },
    defaultVariants: {
      variant: 'default',
      size: 'default',
    },
  },
)

export type ButtonVariants = VariantProps<typeof buttonVariants>
