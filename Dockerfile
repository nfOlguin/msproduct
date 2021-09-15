FROM _path_to_image_
ARG HOME=/home/falabella
RUN mkdir -p ${HOME}/app
COPY --chown=falabella:falabella build/libs/*.jar ${HOME}/app/app.jar
WORKDIR ${HOME}/app