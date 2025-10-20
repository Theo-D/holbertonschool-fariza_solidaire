export async function login(email, password) {
  try {
    const response = await fetch('http://localhost:8080/auth/login', {
      method: 'POST',
      headers: {
        'accept': '*/*',
        'Content-Type': 'application/json'
      },
      // body: '{\n  "emailAddress": "admin@zafira.com",\n  "password": "admin"\n}',
      body: JSON.stringify({
        'emailAddress': email,
        'password': password
      })
    });

    const token = await response.json();
    return token;
  } catch (error) {
    console.error("Error during login:", error);
    throw error;
  }
}
