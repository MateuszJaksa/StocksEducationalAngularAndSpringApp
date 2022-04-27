FROM node:16-alpine AS build

WORKDIR /app

COPY src ./src/
COPY . .

RUN npm install
RUN npm run build


FROM nginx:1.21.5-alpine

COPY --from=build /app/dist/app-front /usr/share/nginx/html
