declare function initEnv():any;

export const environment = {
  production: false,
  base_url: (window as { [key: string]: any })['env']['base_url'] || "http://localhost:8080/api",
  //base_url:initEnv() || "http://localhost:8080/api"
};


