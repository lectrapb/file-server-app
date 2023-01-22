export const environment = {
  production: true,
  base_url: (window as { [key: string]: any })['env']['base_url'] || "http://localhost:8080/api"
};
