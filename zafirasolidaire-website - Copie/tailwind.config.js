import daisyui from 'daisyui'
import plugin from 'tailwindcss/plugin'

export default {
  content: ['./index.html', './src/**/*.{js,jsx,ts,tsx,html}'],
  theme: {
    extend: {
       colors: {
      'brand-blue': '#42AAE1',
      'brand-pink': '#E82B89',
      'brand-yellow': '#FCD916',
    }
    },
  },
  plugins: [
    daisyui,
    plugin(function({ addUtilities }) {
      addUtilities({
        '.bg-linear-to-br': {
          'background-image': 'linear-gradient(to bottom right, var(--tw-gradient-stops))',
        },
        '.bg-linear-to-tr': {
          'background-image': 'linear-gradient(to top right, var(--tw-gradient-stops))',
        },
        '.bg-linear-to-bl': {
          'background-image': 'linear-gradient(to bottom left, var(--tw-gradient-stops))',
        },
        '.bg-linear-to-tl': {
          'background-image': 'linear-gradient(to top left, var(--tw-gradient-stops))',
        },
      })
    })
  ],
}
