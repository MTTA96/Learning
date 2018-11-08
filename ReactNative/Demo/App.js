import React from 'react';
import { StyleSheet, Image, Text, View } from 'react-native';
import { createStore, applyMiddleware } from 'redux'
import { Provider, connect } from 'react-redux'
import axios from 'axios'
import axiosMiddleware from 'redux-axios-middleware'
import reducers from './src/reducers'

import SigninScreen from './src/screens/SigninScreen'

const client = axios.create({
  baseURL: 'https://www.themoviedb.org',
  responseType: 'json'
})

const store = createStore(reducers, applyMiddleware(axiosMiddleware(client)))

export default class App extends React.Component {
  render() {
    return (
      <Provider store = {store}>
        <View style={styles.container}>
          <SigninScreen />
        </View>
      </Provider>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    // backgroundColor: '#fff',
    // alignItems: 'center',
    // justifyContent: 'center',
  }, 
});
