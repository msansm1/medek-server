
angular.module('medekApp', ['ui.router', 'medekApp.controllers', 
                          'ui.bootstrap', 'pascalprecht.translate'])
         .config(function($translateProvider) {
            $translateProvider.useStaticFilesLoader({
                prefix: 'app/languages/',
                suffix: '.json'
            });
            $translateProvider.preferredLanguage('en');
        })
        .config(function($stateProvider, $urlRouterProvider, $httpProvider, $locationProvider) {
            $locationProvider.html5Mode(true);
            // default route
            $urlRouterProvider.otherwise("/");
            // states definitions
            $stateProvider
            .state('login', {
              url: '/',
              templateUrl : 'app/views/login.html',
              controller : 'AuthController'
            })
            .state('home', {
              url: '/home',
              templateUrl : 'app/views/home.html',
              controller : 'HomeController'
            })
            .state('books', {
                url: '/books',
                templateUrl : 'app/views/book/books.html',
                controller : 'BooksController'
            })
            .state('books.add', {
                url: '/add',
                templateUrl : 'app/views/book/addbook.html',
                controller : 'BookController'
            })
            .state('books.detail', {
                url: '/:bookId',
                templateUrl : 'app/views/book/book.html',
                controller : 'BookController'
            })
            .state('books.edit', {
                url: '/edit/:bookId',
                templateUrl : 'app/views/book/editbook.html',
                controller : 'BookController'
            })
            .state('albums', {
                url: '/albums',
                templateUrl : 'app/views/album/albums.html',
                controller : 'AlbumsController'
            })
            .state('albums.detail', {
                url: '/:albumId',
                templateUrl : 'app/views/album/album.html',
                controller : 'AlbumController'
            })
            .state('albums.edit', {
                url: '/edit/:albumId',
                templateUrl : 'app/views/album/editalbum.html',
                controller : 'AlbumController'
            })
            .state('movies', {
                url: '/movies',
                templateUrl : 'app/views/movie/movies.html',
                controller : 'MoviesController'
            })
            .state('movies.detail', {
                url: '/:movieId',
                templateUrl : 'app/views/movie/movie.html',
                controller : 'MovieController'
            })
            .state('movies.edit', {
                url: '/edit/:movieId',
                templateUrl : 'app/views/movie/editmovie.html',
                controller : 'MovieController'
            })
            .state('series', {
                url: '/series',
                templateUrl : 'app/views/series/series.html',
                controller : 'SeriesController'
            })
            .state('series.detail', {
                url: '/:serieId',
                templateUrl : 'app/views/series/serie.html',
                controller : 'SerieController'
            })
            .state('series.edit', {
                url: '/edit/:serieId',
                templateUrl : 'app/views/series/editserie.html',
                controller : 'SerieController'
            })
            .state('error', {
                url: 'error',
                resolve: {
                    errorObj: [function () {
                        return this.self.error;
                    }]
                },
                controller: 'ErrorCtrl',
                templateUrl: 'error.html' // displays an error message
            });
            /* CORS... */
            /* http://stackoverflow.com/questions/17289195/angularjs-post-data-to-external-rest-api */
            $httpProvider.defaults.useXDomain = true;
            delete $httpProvider.defaults.headers.common['X-Requested-With'];
        })
        .run(function ($rootScope) {
            $rootScope.isConnected = false; //global variable
            
            $rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams){ 
                console.log("start change state : "+fromState.url+" => "+toState.url);
            });
            
            $rootScope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams){ 
                console.log("change state success : "+fromState.url+" => "+toState.url);
            });
            
            $rootScope.$on('$stateNotFound', function(event, unfoundState, fromState, fromParams){ 
                console.log('$stateNotFound : '+unfoundState.to); // "lazy.state"
                console.log('$stateNotFound : '+unfoundState.toParams); // {a:1, b:2}
                console.log('$stateNotFound : '+unfoundState.options); // {inherit:false} + default options
            });
            
            $rootScope.$on('$stateChangeError', function (event, toState, toParams, fromState, fromParams, error) {
                //event.preventDefault();
                //$state.get('error').error = { code: 123, description: 'Exception stack trace' }
                console.log("$stateChangeError : event => "+event);
                console.log("error stack : "+error);
                //return $state.go('error');
              });
            

            $rootScope.isActive = function(stateName) {
                //return $state.includes(stateName);
                return false;
            };
        });

var ctrls = angular.module('medekApp.controllers', ['medekApp.services']);

var svcs = angular.module('medekApp.services', []);

//compareTo directive
var compareTo = function() {
    return {
      require: "ngModel",
      scope: {
        otherModelValue: "=compareTo"
      },
      link: function(scope, element, attributes, ngModel) {

        ngModel.$validators.compareTo = function(modelValue) {
          return modelValue == scope.otherModelValue;
        };

        scope.$watch("otherModelValue", function() {
          ngModel.$validate();
        });
      }
    };
  };

app.directive("compareTo", compareTo);

// ngReally directive
/**
* A generic confirmation for risky actions.
* Usage: Add attributes: ng-really-message="Are you sure"? ng-really-click="takeAction()" function
*/
app.directive('ngReallyClick', [function() {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            element.bind('click', function() {
                var message = attrs.ngReallyMessage;
                if (message && confirm(message)) {
                    scope.$apply(attrs.ngReallyClick);
                }
            });
        }
    }
}]); 