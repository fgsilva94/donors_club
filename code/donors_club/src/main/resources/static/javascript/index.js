import { getElement, setStorageItem, getStorageItem } from "./utils.js";

const adsField = getElement(".ads");
const name = getElement(".name");

window.addEventListener("DOMContentLoaded", async () => {
  name.innerHTML = ` ${
    getStorageItem("userName") ? getStorageItem("userName") : ""
  }`;
  let ads;
  try {
    ads = await $.ajax({
      url: `/api/ads`,
      method: "get",
      dataType: "json",
    });
  } catch (error) {}

  adsField.innerHTML = ads
    .map((ad) => {
      return `<a href="./adPost.html">
          <article class="ad" data-id="${ad.id}">
            <img
              src="https://upload.wikimedia.org/wikipedia/commons/thumb/f/f7/DOG-HUSKY_23JUL00.jpg/220px-DOG-HUSKY_23JUL00.jpg"
              alt="test img"
            />
            <span>${ad.title}</span>
          </article>
        </a>`;
    })
    .join("");
});

adsField.addEventListener("click", function (e) {
  let id;
  if (e.target.parentElement.dataset.id) {
    id = e.target.parentElement.dataset.id;
    setStorageItem("adId", id);
  } else if (e.target.dataset.id) {
    id = e.target.dataset.id;
    setStorageItem("adId", id);
  }
});
