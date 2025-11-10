import React, { useRef, useEffect } from "react";

export default function BackgroundShapes() {
  const canvasRef = useRef(null);
  const shapes = useRef([]);
  const shapeCount = 8;

  useEffect(() => {
    const canvas = canvasRef.current;
    const ctx = canvas.getContext("2d");
    let animationFrame;

    const resize = () => {
      canvas.width = window.innerWidth;
      canvas.height = window.innerHeight;
    };
    resize();
    window.addEventListener("resize", resize);

    // Create random shapes
    const colors = ["#ffee8c", "#e880b4", "#7bbde0"];
    for (let i = 0; i < shapeCount; i++) {
      shapes.current.push({
        x: Math.random() * canvas.width,
        y: Math.random() * canvas.height,
        r: 15 + Math.random() * 200, // Shape size
        dx: (Math.random() - 0.5) * 0.8, //Shape speed on x axis
        dy: (Math.random() - 0.5) * 0.8, // Shpae speed on y axis
        color: colors[Math.floor(Math.random() * colors.length)],
      });
    }

    const draw = () => {
      ctx.clearRect(0, 0, canvas.width, canvas.height);
      shapes.current.forEach((shape) => {
        ctx.fillStyle = shape.color;
        ctx.beginPath();
        ctx.arc(shape.x, shape.y, shape.r, 0, Math.PI * 2);
        ctx.fill();

        // Move shapes
        shape.x += shape.dx;
        shape.y += shape.dy;

        // Prevent shapes from exiting the screen
        if (shape.x < 0 || shape.x > canvas.width) shape.dx *= -1;
        if (shape.y < 0 || shape.y > canvas.height) shape.dy *= -1;
      });

      animationFrame = requestAnimationFrame(draw);
    };

    draw();

    return () => {
      cancelAnimationFrame(animationFrame);
      window.removeEventListener("resize", resize);
    };
  }, []);

  return (
    <canvas
      ref={canvasRef}
      className="fixed inset-0 -z-10 pointer-events-none"
    />
  );
}
