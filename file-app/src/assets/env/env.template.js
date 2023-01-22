(function(window) {
  window.env = window.env || {};

  // Environment variables
  window["env"]["base_url"] = "${API_URL}";
  console.log(window);
})(this);

