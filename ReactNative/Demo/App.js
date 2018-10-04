import React from 'react';
import { StyleSheet, Image, Text, View } from 'react-native';
import SigninScreen from './src/screens/SigninScreen'

export default class App extends React.Component {
  render() {
    return (
      <View style={styles.container}>
        <SigninScreen/>
      </View>
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
