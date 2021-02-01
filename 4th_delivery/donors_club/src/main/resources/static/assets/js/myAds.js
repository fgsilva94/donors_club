import { getElement, getStorageItem } from "./utils.js";

const adsField = getElement(".ads");

window.addEventListener("DOMContentLoaded", async () => {
  try {
    let ads = await $.ajax({
      url: `/api/ads/my_adposts/${getStorageItem("userId")}`,
      method: "get",
      dataType: "json",
    });

    let htmlAds = "";
    for (const ad of ads) {
      htmlAds += `<div class="col ad mb-5" data-id="${ad.id}">
                        <div class="card shadow-sm">
                            <a href="./adPost.html">
                                    <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/f/f7/DOG-HUSKY_23JUL00.jpg/220px-DOG-HUSKY_23JUL00.jpg"
                                        alt="test img"/>
                                    <div class="card-body">
                                        <p class="card-text">${ad.title}</p>
                                        <div class="d-flex justify-content-between align-items-center">
                                            <small class="text-muted">${ad.city}</small>
                                            <small class="text-muted">${ad.pubDate}</small>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </div>`;
    }
    adsField.innerHTML = htmlAds;
  } catch (error) {}
});

adsField.addEventListener("click", function (e) {
  let id;

  if (e.target.parentElement.parentElement.dataset.id) {
    id = e.target.parentElement.parentElement.dataset.id;
    sessionStorage.setItem("adId", id);
  } else if (e.target.parentElement.parentElement.parentElement.dataset.id) {
    id = e.target.parentElement.parentElement.parentElement.dataset.id;
    sessionStorage.setItem("adId", id);
  } else if (
    e.target.parentElement.parentElement.parentElement.parentElement.dataset.id
  ) {
    id =
      e.target.parentElement.parentElement.parentElement.parentElement.dataset
        .id;
    sessionStorage.setItem("adId", id);
  } else if (
    e.target.parentElement.parentElement.parentElement.parentElement
      .parentElement.dataset.id
  ) {
    id =
      e.target.parentElement.parentElement.parentElement.parentElement
        .parentElement.dataset.id;
    sessionStorage.setItem("adId", id);
  }
});
