gwt.threejs
===========

#### Native Google Web Toolkit port of [three.js](https://github.com/mrdoob/three.js), Javascript 3D Engine ####
At the beginning, there was no specific aim for this project. I just wanted to see how will this library look and perform in GWT environment. At the end it gave me understanding of how three.js works.

Please refer to [original library](https://github.com/mrdoob/three.js) from [mrdoob](https://github.com/mrdoob) for API reference and great demos.

gwt.threejs contains only a small subset of all the features original library has.
Although gwt.threejs preserves original logic, somehow GWT compiler generates extremely slow javascript code compared to original three.js.

### Canvas (Context 2D) ###
* [gwt.threejs example “Earth”](http://vatula.github.com/gwt.threejs/examples/canvas_geometry_earth/Globe.html)
* [Original three.js example “Earth”](http://mrdoob.github.com/three.js/examples/canvas_geometry_earth.html)

Sadly enought, but GWT version shows only half FPS of original version.
