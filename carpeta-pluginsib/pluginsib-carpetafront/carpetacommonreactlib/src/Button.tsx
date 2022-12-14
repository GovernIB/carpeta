// src/Button.tsx
import React, { ButtonHTMLAttributes } from 'react'

interface Props extends ButtonHTMLAttributes<HTMLButtonElement> {
  bgColor?: string
  textColor?: string
}

export const Button: React.FC<Props> = ({
  bgColor = 'yellow',
  textColor = 'black',
  children,
  ...rest
}) => {
  return (
    <button style={{ backgroundColor: bgColor, color: textColor }} {...rest}>
      {children}
    </button>
  )
}