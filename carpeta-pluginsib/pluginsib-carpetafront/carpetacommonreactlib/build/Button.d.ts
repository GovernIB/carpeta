import React, { ButtonHTMLAttributes } from 'react';
interface Props extends ButtonHTMLAttributes<HTMLButtonElement> {
    bgColor?: string;
    textColor?: string;
}
export declare const Button: React.FC<Props>;
export {};
