import { getElement, getStorageItem } from "./utils.js";

const _user = getElement(".user");

window.addEventListener("DOMContentLoaded", async () => {
  let user;
  const id = getStorageItem("userId");
  try {
    user = await $.ajax({
      url: `/api/users/${id}`,
      method: "get",
      dataType: "json",
    });

    const {
      name,
      email,
      phoneNumber,
      street,
      city: {
        name: cityName,
        district: { name: districtName },
      },
    } = user;

    _user.innerHTML = `
      <p>Name: ${name}</p>
      <p>Email: ${email}</p>
      <p>Phone Number: ${phoneNumber}</p>
      <p>Address: ${street}, ${cityName}, ${districtName}</p>`;
  } catch (error) {}
});
