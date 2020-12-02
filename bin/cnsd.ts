#!/usr/bin/env node
import 'source-map-support/register';
import * as cdk from '@aws-cdk/core';
import { CnsdStack } from '../lib/cnsd-stack';

const app = new cdk.App();
new CnsdStack(app, 'CnsdStack');


